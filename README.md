# 🚗 Car Marketplace

Car Marketplace is an Android application that displays a list of cars with detailed information. Users can browse available vehicles, view their specifications, and interact with the list using swipe gestures. The app uses `Retrofit` to fetch data from a remote JSON file and `Picasso` to load images.

---

## 🛠 Technologies Used

- **Kotlin**
- **Retrofit** – for HTTP requests
- **Gson** – for parsing JSON data
- **RecyclerView** – to display car items in a list
- **Picasso** – for image loading
- **Material Design Components** – for Snackbar and other UI elements

---

## 📱 Features

- Load car data from a remote API
- Display car details including name, model, release year, price, engine size, and image
- Tap on a car to view more detailed information
- Swipe gestures:
  - ➡️ Swipe Right – Add to Favorites (visual feedback only)
  - ⬅️ Swipe Left – Remove from the list
- Load car images from the internet using Picasso

---

## 🌐 API Source

The car data is fetched from the following GitHub-hosted JSON file:  

---

## 🚀 Getting Started

### Prerequisites

- Android Studio installed
- Internet connection

### Installation

1. Clone this repository:
   ```bash
   git clone https://github.com/YOUR_USERNAME/CarMarketplace.git
   cd CarMarketplace
👤 Author
Nika Chigladze
