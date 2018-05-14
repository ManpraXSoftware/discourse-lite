# discourse-lite
Android Discourse client which consumes [Discourse rest api](https://docs.discourse.org/).It written in Android using native components. It contains only basic implementation.

### Quick-Start
 * Clone this repository.
 * Include discourse as a module in your project.
 * Create DiscourseConfig by your server url (Base Url), UserName, and API KEY.
 * API Key can be found in your Discourse Admin Panel.[Setup](https://github.com/discourse/wp-discourse/wiki/Setup)
 * To start Discourse activity, Just Call `Discourse.open(Context context,DiscourseConfig config)` (It takes DiscourseConfig instance as argument)
 
 
 ```
  DiscourseConfig config = new DiscourseConfig("https://meta.discourse.org/", 
                              "userName", "cdb7475c5eca96a149bf3b81aa4815fe3b87");
  // From Activity
  Discourse.open(MainActivity.this, config);
  // OR 
  // From Fragment
  Discourse.open(getActivity(),config);
 ```
 ### Third Party Dependency
 * [Retrofit](https://github.com/square/retrofit) (For networking)
 * [WYSIWYG-Editor](https://github.com/irshuLx/Android-WYSIWYG-Editor) (Rich Text Editor)
 * [Glide](https://github.com/bumptech/glide)  (For Image Loading and Caching)
 * [Circular-Image-View](https://github.com/hdodenhof/CircleImageView)  (For Circular Image View)