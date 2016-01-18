# Pre-work - *Name of App Here*

**Name of your app** is an android app that allows building a todo list and basic todo items management functionality including adding new items, editing and deleting an existing item.

Submitted by: Gaurav Londhe

Time spent: 8 hours spent in total

## User Stories

The following **required** functionality is completed:

* [ ] User can **successfully add from the todo list
* [ ] User can **tap a todo item in the list and bring up an edit screen for the todo item** and then have any changes to the text reflected in the todo list.
* [ ] User can **persist todo items** and retrieve them properly on app restart

NOTE: remove items** from the todo list - This functionality broke at last min was not able to figure out why. Basically for Edit functionality, I was using setOnItemClickListener but it  is not getting invoked on clicking item. So, had to get edit functionality moved to setOnItemLongClickListener and remove item into setOnItemClickListener which is broken.

The following **optional** features are implemented:

* [ ] Persist the todo items [into SQLite] instead of a text file
* [ ] Improve style of the todo items in the list [using a custom adapter](http://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView)
* [ ] Add support for completion due dates for todo items (and display within listview item)
* [ ] Tweak the style improving the UI / UX, play with colors, images or backgrounds
added app launcher image.

The following **additional** features are implemented:

* [ ] Along with date, it will also display more user friendly msg like - Due today, Pas due or due in Future.
* [ ] Sort in oder of Due today, due in Future and Past due.
* [ ] Added color coding based on Type of item.

## Video Walkthrough 

Here's a walkthrough of implemented user stories:

<img src='http://imgur.com/LBf8am1' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

Describe any challenges encountered while building the app.

## License

    Copyright [yyyy] [name of copyright owner]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.