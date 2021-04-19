# firebaseTest
Prepared by Jae Lee from Costco Wholesale.
jaewoolee@costco.com

There are three branches: 
1. programmatical:
- Programmatically initializes Firebase project. 

2. withGoogleServicesJson:
- Initialize Firebase project with 'google-services.json' file.

3. programmaticalWithDefaultGoogleServicesJson:
- Initialize a default Firebase project with 'google-services.json' file and try to dynamically switch to different one.

As we have discussed, I verified that Firebase analytics doesn't get initialized when I programmatically initialize Firebase without 'google-services.json'.
The error log is: E/FA: Missing google_app_id. Firebase Analytics disabled. See https://goo.gl/NAOOOI
Issues are:
1. Analytics doesn't get initialized.
2. Since analytics doesn't get initialized, setting the user properties doesn't work.
3. Since we can't set the user properties, we can't retrieve the data with specific user properties (e.g. locale).

I also verified I can fetch the conditioned data correctly from the 'withGoogleServicesJson' branch.

On the third branch with default project loaded and trying to dynamically change, as I've brought up before, I get a crash:
-     java.lang.IllegalStateException: FirebaseApp name [DEFAULT] already exists!
        at com.google.android.gms.common.internal.Preconditions.checkState(com.google.android.gms:play-services-basement@@17.2.1:29)
        at com.google.firebase.FirebaseApp.initializeApp(FirebaseApp.java:294)
        at com.google.firebase.FirebaseApp.initializeApp(FirebaseApp.java:267)

I created a new firebase project using my personal google account:
https://console.firebase.google.com/u/0/project/jw-firebase-proj/overview
and
https://console.firebase.google.com/u/0/project/jw-firebase-proj2/overview

and this requires permission for anyone else to access it so let me know if they request it or if you need it.

Basically the only key/value that's up in that project is this one key:
- key: test_key
- value: default_value
- value with a condition called 'test_condition' set to 'test': test_value

Please contact Jae Lee at jaewoolee@costco.com for any questions.
