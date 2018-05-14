# discourse-lite
Android Discourse client which consumes discourse rest api.It written in Android using native components. It contains only basic implementation.

### Quick-Start
 * Clone this repository.
 * Include discourseplugin as a library in your project.
 * Create DiscourseConfig by your server url (Base Url), UserName, and API KEY (Found in Discourse Admin Panel).
 * To start Discourse activity, Just Call `Discourse.open(Context context,DiscourseConfig config)` (It takes DiscourseConfig instance as argument)
 ```
  DiscourseConfig config = new DiscourseConfig("https://meta.discourse.org/", 
                              "userName", "cdb7475c5eca96a149bf3b81aa4815fe3b87");
  Discourse.open(MainActivity.this, config);
 ```
 ### Third Party Dependency
 * [Retrofit](https://github.com/square/retrofit) (For networking)
 * [WYSIWYG-Editor ](https://github.com/irshuLx/Android-WYSIWYG-Editor) (Rich Text Editor)