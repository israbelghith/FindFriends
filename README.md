# 📍 FindFriends

**FindFriends** is an Android application that allows users to request and share GPS locations via SMS. It helps friends and family locate each other by automatically sending GPS coordinates and opening Google Maps to display the exact position.

## 🚀 Main Features

### 📲 Permission Requests
- Upon launch, the app requests permission to send SMS and access location services.

### 📩 Request Sending Interface
- The home screen allows users to enter a phone number and send a location request.

### 📍 Automatic Location Sharing
- When a user receives a location request message, their phone automatically retrieves their GPS coordinates (longitude/latitude) and sends them back via SMS.

### 🗺️ Notifications & Google Maps Integration
- When a user receives an SMS containing a location, a notification appears.
- Clicking on the notification opens Google Maps with the exact contact’s location.

## 🛠️ Technologies Used
- **Language:** Java (Android)
- **Frameworks:** Android SDK
- **SMS Handling:** `SmsManager` for sending, `BroadcastReceiver` for receiving
- **Location Services:** `FusedLocationProviderClient` for GPS coordinates retrieval
- **Notifications:** `NotificationCompat` and `NotificationManagerCompat`

## 🔐 Required Permissions
The app requires the following permissions to function properly:
- **Send and receive SMS**: `SEND_SMS`, `RECEIVE_SMS`, `READ_SMS`
- **Access location services**: `ACCESS_FINE_LOCATION`, `ACCESS_COARSE_LOCATION`
- **Display notifications**: `POST_NOTIFICATIONS`

## 📖 How to Use
1. **Launch the app** and accept the required permissions.
2. **Enter a phone number** and click the **"Send"** button.
3. **The other user receives the request** and automatically sends back their location.
4. **A notification appears**, allowing you to open Google Maps with the exact location.

## 📩 Contact
If you have any questions, feel free to open an **issue** or contact me on GitHub.  
