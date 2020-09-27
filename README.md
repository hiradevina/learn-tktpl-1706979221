# Lab Report 2: Testing
Unit Test and Instrumented Test with Kotlin

[Repository](https://github.com/hiradevina/learn-tktpl-1706979221/tree/lab-2) 

![Example](https://lh3.googleusercontent.com/pw/ACtC-3dpFX4-g3jpZmrZo_89A2EKJkcQQ25UhG25rHpCH1x9g6_aJBTPS7OX4bkUhz9_NhjgKmxbuPaJyYBHe4lfSyu-jzvf3vRLbbyCv_ydTKAKLtLIsAZSKuV8mHIXBkKTesWOvHAyId-M4U0K5CcTAraR=w407-h846-no?authuser=0)
## Unit Test
## Instrumented Test
## Self sign APK
Android requires that all APKs be digitally signed with a certificate before they are installed on a device or updated. When signing your app, the signing tool attaches the certificate to your app. The certificate associates the APK or app bundle to you and your corresponding private key. This helps Android ensure that any future updates to your app are authentic and come from the original author. 
As stated in the [Android documentation](https://developer.android.com/studio/publish/app-signing#sign-apk), here is how I signed my app:
1. In the menu bar, click Build > Build > Generate Signed Bundle/APK.
2. In the Generate Signed Bundle or APK dialog, select APK and click Next. 
3. Below the field for Key store path, click Create new.
![Create new keystore file](https://lh3.googleusercontent.com/PnM3YihbXxle065txrdGsO4rfZWWTXsUwIS2wC_TO5aiWv3BGljL85ioMnru40KZKZVseGxkx1AZ3zxlU2APtX8lcPt-qcYGirWUvTOXcyoKRyQ2bTR-sB7jZZhnor1IGkyJuNE7GuUPpaMbw1uPQdtcafEcI7bfUqL5Xv68MDMCW8ihmF4jdYFzw7RI-jfzRNKQ_UmhcaUOpNc9HAUTi0W27EajfVpoFd5DwvqDuwQ2SfVjVEI_fILzhN2ls7Pogd4HBnXckvPWLpKHJ4k0LkTynl4u6oWrs9pg6JyTJledJy1w9xapq6waB-XwvLwmn2m6mn9ig0pP1MMPYp20uaHdpKmva5BXkiqXnQN4jgRSLkwV7LvCPS6Tw2o6CDQ3Cbrb7P0B7ZHiyNIkAB3ImGlnv1HwuTUQzOpA49qZX9ChRwpkfBpID4UYVGp9R2yLg6XRnFoWGaHDiLsF8kTcOo9DzAvdgxKMNIP5vF0bftNBOyN4YUorAIY6ZytuOG8oBhuvJK3aGQzpeIFCsFdtdc9eRCuYKC6uOhpRjjD2ScDXv_irBWUyoC2Sh6fBP8JhR66c4sC-xBOViQgmDMTKSz9tXfCFi4pJ3DlYI8yZ0HsPaN646Xbm2PExvExvGB-XJVPted310VAR6RyzVMS1hVi6vbfOSei6hsud9E1kTf1dkcB01R8CgvWDwtaO=w628-h667-no?authuser=0)
- Key store path: Select the location where your keystore should be stored.
- Password: Password for the keystore file.
- Alias: Enter a 'name' for your key.
- Password: Create and confirm a secure password for your key. This should be different from the password you chose for your keystore.
- Validity (years): Set the length of time in years that your key will be valid. Your key should be valid for at least 25 years, so you can sign app updates with the same key through the lifespan of your app.
- Certificate: Enter some information about yourself for your certificate. This information is not displayed in your app, but is included in your certificate as part of the APK to identify whose the owner of the app.
4. Click OK
5. By doing the previous steps, I already have my upload key, the upload key can be used also as the app signing key by putting this lines on `app/build.gradle`
```
signingConfigs {
           config {
               keyAlias key0
               keyPassword [fill in with the key password]
               storeFile file('keystore.jks')
               storePassword [fill in with the keystore password]
               v2SigningEnabled false
           }
```
I used the v1 signing because the v2 signing won't let changes of the app after the app is signed.
### Hiding key information from the source code
To avoid the credentials of your app being used by unauthorized people, we should not let the keystore nor the key file being exposed in the source code. This is how I separate the credentials from the source code:
1. Create  `key.properties` and add these lines
```
storePassword=[fill in with the keystore password]
keyPassword=[fill in with the key password]
keyAlias=key0
storeFile=keystore.jks
```
2. Make sure that your `keystore.jks` is located in the same directory as the `build.gradle` file (mine is in the `app` folder)
3. On `app/build.gradle` access the `key.properties` file by typing these lines
```
def keyStoreProperties = new Properties()
def keyStorePropertiesFile = rootProject.file('key.properties')
if (keyStorePropertiesFile.exists()) {
    keyStoreProperties.load(new FileInputStream(keyStorePropertiesFile))
```
4. Modify the `signingConfig` part of your `app/build.gradle` to get the credentials from `key.properties`
```
signingConfigs {
        config {
            keyAlias keyStoreProperties['keyAlias']
            keyPassword keyStoreProperties['keyPassword']
            storeFile keyStoreProperties['storeFile'] ? file(keyStoreProperties['storeFile']) : null
            storePassword keyStoreProperties['storePassword']
            v2SigningEnabled false
        }
    }
```
5. Lastly, put `key.properties` and `app/keystore.jks` in `.gitignore` to prevent the credentials being uploaded to the version control. 
## Install APK
After I signed the APK, I can generate a signed APK and install it on my Android device
![install APK](https://lh3.googleusercontent.com/SLsAS4PP1qi_UgRE5W8OpMegesxAoLIfZi-nGHrIkUzgVhSzGwbisHaweRWSwWqXVgKp2uihdSi4kWLVcjGUyoveeX5uDzLLLNWadNKKDmQK71eYaUUM-o5fPJBXUYOOPZa_v_hs8CRWXlSPJLehRw14cq4v6EqU2NUtCR5CyM1XndB0xDl0TJdL0eEym76sBzwB9hksrNG4wX17IxkibXHceHNIoezStPn-Z4pLHetfUvYRAsGR969GEzSEUkpW0fAS8wfgQPLS25AYpja1keC_sqL5Qc_CnpZPd9J-9-JpuzwKm2s2D_HVcPVlv9VB3U1jXC4udy0iczlSmmtqXa5sFbyN57HCCRKSkZxhnq1W-m1gSbsFMNKrrko-DPETgKz7tGRsmdyW13AD5DegPcKNMhQUy41Dy3x3pNFCf4qKvyBd0icaf8vCGqsjb1dtrdNdDSa9ikkmqbf2bIQARA5hcYUMw3fBDlq8Z9pxRSAK_jlQugndBvq-0y02tTXiryIjeBVDIeFILkMu-dSFAv-3NSkTug9Y4cA8l9C2AQx2jYVotXNyenh0akrrO2f_cI0KklPEUalJHSm4Gl-dDXK42D7b1YykSlL_ZXDhvjcuDyJJVhG0o1q4pmS0j5dqsowPGVW4UJsSZSwhqIzsaOEvhL55ehZeHTF07vuqotX42gXOHDSSjx3HGm9t=w425-h943-no?authuser=0)