# Pixaby


Is an android app that uses search api of Pixaby and listing the Api result.  

The Pixaby utilizing:

    Retrofit2
	RxJava2
	Kotlin
	Glide 
    Android Support Libraries
	Android Architecture Components 

    APP FEATURES: 
    1.) Live Data
    2.) Search the latest Updated data
    

Architecture: MVVM 

PROS:
     
	 * why I choise, It is not a secret that Google positions using MVVM (Model-View-ViewModel) 
      as the most efficient choice of Android architecture.
      
     * At first migrating to MVVM, indeed, increased the readability and stability of the code base.
     * Ideal for a large scale apps or project. 
     
           
    
        
KNOWN ISSUES:
     
	 * While fetching data from API on android P devices, its returning me an error message "CLEARTEXT communication to pixabay.com not permitted by network security policy" 
       where as i had added the code from my side to solve the problem but it still exist, i think there might be some certificates issue from server side. i had added the code to solve the issue form this link : https://stackoverflow.com/questions/45940861/android-8-cleartext-http-traffic-not-permitted 
      
	 * While the APIs are working fine on other devices, its getting issue only in android P device.
	 
	 * I am not good at unit testing so thats why could not add alot of test cases, so kindly ignore this section for the time if possible
	 
	 
	 