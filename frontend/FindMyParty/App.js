import React from "react";
import RegisterFormScreen from './app/screens/RegisterFormScreen';
import AppNavigation from './app/navigation/AppNavigation';
import { useFonts } from 'expo-font';
import MainScreen from "./app/screens/MainScreen";
import LoginScreen from "./app/screens/LoginScreen";

export default function App() {
  
  const [loaded] = useFonts({
    Karantina: require('./app/assets/fonts/Karantina/Karantina-Bold.ttf'),
    RalewayLight: require('./app/assets/fonts/Raleway/static/Raleway-Light.ttf'),
  });

  if (!loaded) {
    return null;
  }

  return ( <AppNavigation/> );
}