## Project demo cách sử dụng Epoxy với data binding trong việc thiết kế một số giao diện Recycler View cơ bản

Đường dẫn tới lib Epoxy: 
```sh
https://github.com/airbnb/epoxy
```

#### 1. Setup
Thêm các phụ thuộc cần thiết vào build.gradle (Module :app)

```sh
plugins {
    id("kotlin-kapt")
}

kapt {
    correctErrorTypes = true
}

android {
   dataBinding {
        enable = true
    }
}

dependencies {
    implementation("com.airbnb.android:epoxy:5.1.4")
    kapt("com.airbnb.android:epoxy-processor:5.1.4")
    implementation("com.airbnb.android:epoxy-databinding:5.1.4")

    // Layout error alignment library for horizontal epoxy model
    implementation("com.github.rubensousa:gravitysnaphelper:2.2.2")

    // Glide
    implementation("com.github.bumptech.glide:glide:4.16.0")
}
```
#### 2. Tạo 1 Interface hoặc object Config cho Epoxy
```sh
@EpoxyDataBindingPattern(rClass = R::class, layoutPrefix = "epoxy")
object EpoxyDataBindingConfig
 ```

- layoutPrefix = "epoxy" tương ứng với việc Epoxy sẽ tạo ra các Models ứng những layout bắt đầu bằng epoxy. VD: epoxy_item_image -> Model được tạo ra sẽ là itemImage hoặc ItemImageBindingModel_() 

#### 3. Tạo Models
- Tạo 1 layout cho item của mình. Ở đây lấy ví dụ là epoxy_item_image.xml

```sh
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:bind="http://schemas.android.com/tools"
    xmlns:tools="http://schemas.android.com/tools">

    <data>
        <variable
            name="url"
            type="String" />

        <variable
            name="isSelected"
            type="Boolean" />

        <variable
            name="onClick"
            type="android.view.View.OnClickListener"/>
    </data>

    <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_marginTop="20dp"
        android:layout_height="wrap_content">

        <ImageView
            tools:background="#683E3E"
            android:id="@+id/imgDB"
            android:layout_width="100dp"
            android:layout_height="100dp"
            android:contentDescription="@null"
            app:layout_constraintEnd_toEndOf="parent"
            bind:imageUrl="@{url}"
            bind:isSelected="@{isSelected}"
            android:onClick="@{onClick}"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent" />
    </androidx.constraintlayout.widget.ConstraintLayout>
</layout>
```

- bind:imageUrl và bind:isSelected được sử dụng để load image bằng Glide

```sh
@BindingAdapter("bind:imageUrl", "bind:isSelected")
fun loadImage(view: ImageView, url: String?, isSelected: Boolean) {
    Log.e("ANCUTKO", isSelected.toString())
    Glide.with(view.context)
        .load(if (!isSelected) Uri.parse("file:///android_asset/" + url) else Uri.parse("file:///android_asset/background/50.webp"))
        .error(Uri.parse("file:///android_asset/background/50.webp")).into(view)
}
```

Lưu ý sau khi tạo, nhớ build lại Project để dataBinding và Epoxy tạo ra các Models cho mình

- Tạo 1 data class để lấy dữ liệu cần thiết
```sh
data class Image(
    val id: Int,
    val src: String,
    var isSelected: Boolean = false
)
```

#### 4. Tạo 1 EpoxyRecyclerView ở Activity muốn sử dụng RecyclerView
```sh
<com.airbnb.epoxy.EpoxyRecyclerView
            android:id="@+id/verticalRecyclerView"
            android:layout_width="match_parent"
            android:layout_height="0dp"
            android:layout_marginBottom="20dp"
            android:orientation="vertical"
            app:layoutManager="androidx.recyclerview.widget.GridLayoutManager"
            app:layout_constraintBottom_toTopOf="@id/bottomNav"
            app:layout_constraintTop_toTopOf="parent"
            app:spanCount="2"
            tools:itemCount="10"
            tools:listitem="@layout/epoxy_item_image" />
```

#### 5. Tạo 1 Controller cho EpoxyRecyclerView

Cách 1: Tạo 1 class Controller

```sh
package com.rekoj134.epoxydemonew.epoxy.controller

import android.util.Log
import com.airbnb.epoxy.AsyncEpoxyController
import com.airbnb.epoxy.EpoxyController
import com.rekoj134.epoxydemonew.itemImage
import com.rekoj134.epoxydemonew.model.Image

// Tạo 1 class kế thừa EpoxyController hoặc AsyncEpoxyController
// Trong lần đâu tiên, cả EpoxyController và AsyncEpoxyController đều sẽ xây dựng các Models để đưa lên giao diện cũng như tạo hashCode cho nó để kiểm tra sự thay đổi sau này trong luồng chính.
// Từ lần 2 trở đi, AsyncEpoxyController sẽ thực hiện điều đó thông qua background nên hiệu suất sẽ tốt hơn. Tuy nhiên cần đảm bảo được sự an toàn luồng trong việc xử lý dữ liệu khi sử dụng AsyncEpoxyController.
class VerticalListController : EpoxyController() {
    // List này lưu trữ những đối tượng bạn muốn hiển thị lên Recycler View
    var listImage: ArrayList<Image> = ArrayList()
        set(value) {
            field = value
            requestModelBuild()
        }

    // Set này để xử lý những đối tượng bạn muốn thay đổi trạng thái khi click
    var setImageSelected = HashSet<Int>()

    // Khi kế thừa EpoxyController, cần override phương thức buildModels.
    // Tại đây chúng ta sẽ cho Epoxy Recycler View biết mình muốn hiển thị dữ liệu theo thứ tự cũng như cách thức thế nào
    override fun buildModels() {
        listImage.forEach { item ->
            itemImage {
                // Lưu ý id của mỗi Model phải khác nhau
                id(item.id)
                url(item.src)
                isFavorite(this@VerticalListController.setImageSelected.contains(item.id))
                onClick { _ ->
                    if (this@VerticalListController.setImageSelected.contains(item.id)) this@VerticalListController.setImageSelected.remove(item.id)
                    else this@VerticalListController.setImageSelected.add(item.id)
                    // Sau khi thay đổi trạng thái của Model. Hãy gọi requestModelBuild() để Epoxy nhận biết sự thay đổi và build lại Model đấy
                    this@VerticalListController.requestModelBuild()
                }
            }
        }
    }
}
```

Sau đó gọi setControllerAndBuildModels ở Epoxy RecyclerView. Và thế là xong =))) 
(Cách 2 dành cho trường hợp list không quá phức tạp và sẽ còn ngắn gọn hơn nữa) 

```sh
binding.verticalRecyclerView.setControllerAndBuildModels(VerticalListController())

// Set dữ liệu cho list, Khi set dữ liệu cho list chúng ta đã gọi requestModelBuild() vậy nên Controller sẽ build lại những item bị thay đổi trong đó.
controller.listImage = DataUtil.getVerticalListData1()
```

Cách 2: Sử dụng withModels

```sh
var setImageSelected = HashSet<Int>()

binding.verticalRecyclerView.withModels {
    listImage.forEach { item ->
        itemImage {
            id(item.id)
            url(item.src)
            isFavorite(setImageSelected.contains(item.id))
            onClick { _ ->
                if (setImageSelected.contains(item.id)) setImageSelected.remove(item.id)
                else setImageSelected.add(item.id)
                requestModelBuild()
            }
        }

//         Một cách khác để build Model
//         listImage.forEach { item ->
//             ItemImageBindingModel_()
//                   .id(item.id)
//                   .url(item.src)
//                   .isFavorite(setImageSelected.contains(item.id))
//                   .onClick {
//                        if (setImageSelected.contains(item.id)) setImageSelected.remove(item.id)
//                        else setImageSelected.add(item.id)
//                        requestModelBuild()
//                   }
//                   .addTo(this@withModels)
//        }
    }
}
```

# THE END

### Vậy là bạn đã nắm qua được cách sử dụng Epoxy để xử lý các giao diện list cơ bản. 
### Hãy thử nghiệm các case trong project demo trên để nắm rõ hơn nữa sức mạnh của thư viện tuyệt vời này, các case trong project bao gồm:
    - Basic vertical list + Move item
    - Basic horizontal list
    - Multi type item with nested list (unlimit horizontal lists inside a vertical list)
    - Multi type item + custom span size in grid list
    - List with expandable items that have list in expanded content


