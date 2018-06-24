# Complex RecyclerView in Kotlin.

[![Twitter](https://img.shields.io/badge/Twitter-@saveendhiman-blue.svg?style=flat)](https://twitter.com/saveendhiman)

[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)


Hi guys, I have made demo application for ComplexRecyclerView in Kotlin. This is the open project for any contributer who want to improve something here.

This Sample show list of users along with their items with pagination and many other things.
offset : Position where you need to start your users.
limit: number of users you want to fetch in single hit.

1. User items images are square.
2. Horizontal and vertical spacings between every image are equal.
3. If items are even, then display them 2 in each row.
4. If items are odd, display the first one to span the full row, and show the remaining spanning 2 in each row.
5. Implemented pagination.

<p align="center">
  <img src="https://user-images.githubusercontent.com/6761640/41692821-ee978692-751f-11e8-82ca-2d8093fd0f1a.gif" alt=“Complex Recycler">
</p>

You can checkout [Youtube] video. 


It usage of following libraries:

* [Retrofit2] for REST API.

* [RX java] for background process and Retrofit integration.

* [Dagger2] for dependency injection.

* [Glide] for image loading.

* [Fabric] for crashlytics.

* [Timber] for logging.

It uses MVP (Model View Presenter) pattern. MVP is a derivative from the well known MVC (Model View Controller), which for a while now is gaining importance in the development of Android applications.This project also contains basic utility classes required for day to day programming.

Utils classes.

#Start from

minSdkVersion 16

#LICENSE

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.

#Authors

[Saveen Dhiman] original Author


[Saveen Dhiman]:        https://github.com/saveendhiman

[Retrofit2]: 		https://square.github.io/retrofit
[RX java]:		https://github.com/ReactiveX/RxJava
[Dagger2]: 		https://google.github.io/dagger
[Glide]:              https://github.com/bumptech/glide
[Fabric]:               https://get.fabric.io/#
[Timber]:               https://github.com/JakeWharton/timber

[Youtube]:        https://youtu.be/SSvEaS0zYPA

