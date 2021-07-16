
# Imaginato Task


### Screens

- Splash screen. (Show for 2 seconds)
- Map screen.
- User Details screen.

### Map Screen

- Random user api. Use API to get users data with their location.
- Provide an option to refresh data.
- Show the horizontal list or pager at the bottom of the screen to show a list of users.
- And above the list show a map screen. Inside that shows the user’s location pin.
- Highlight current user pin on map.
- On click of the pin, open the user details screen.

### User Details Screen
- We will show the user picture and his personal details and address.

### Technical Specifications

- Kotlin
- Navigation graph
- MVVM
- Hilt - DI
- Rest API client: Retrofit
- Google map
- Random user api call: https://randomuser.me/api/?results=20



Change build configuration `app.properties`

```bash

#Development App URLs
APP_DEV_URL="https://randomuser.me"

#Production App URLs
APP_PRODUCTION_URL="https://randomuser.me"

#Google map key
GOOGLE_MAP_KEY_VALUE = XXXXXXXXXXXXXXXXXXXXXXX. // Replace your google map key here
```

[Download APK](https://bit.ly/36MBXgF)
