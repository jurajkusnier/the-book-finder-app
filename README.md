# The Book Finder App

## About
The app displays the search results for a search query using [Google Books Public API](https://developers.google.com/books/docs/v1/using). I developed this app during 2h of the coding challenge.  

## Codebase
I wrote this app in Kotlin using MVVM pattern and Android Architecture Components like ViewModel and LiveData. I implemented a few 3rd party libraries for network communication and image loading.   

## Dependencies
 * [Retrofit 2](http://square.github.io/retrofit) is a REST Client for Android and Java by Square. It's stable, actively developed and indirectly advertised by Google.
 * [RxJava](https://github.com/ReactiveX/RxJava) is a library for composing asynchronous and event-based programs by using observable sequences. I am using RxJava over Android Callback because it's simpler and more robust solution.
 * [RecycleView](https://developer.android.com/guide/topics/ui/layout/recyclerview) widget is a more advanced and flexible version of ListView. It's a perfect solution for displaying display a scrolling list of elements based on large data sets (or data that frequently changes).
 * [Glide](https://github.com/bumptech/glide) is a fast and efficient open source media management and image loading framework for Android.

## Next Steps
The basic book searching feature is working good, however, the app needs a lot of improvements that weren't implemented because of lack of time.
For example:
  * UI improvements
    * showing network errors
    * handling empty results
    * loading indicator
  * Unit tests
