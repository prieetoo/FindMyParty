import React from "react";
import AppNavigation from './app/navigation/AppNavigation';
import { useFonts } from 'expo-font';

export default function App() {
  
  const [loaded] = useFonts({
    RalewayTitle: require('./app/assets/fonts/Raleway/static/Raleway-Light.ttf'),
    RalewayUI: require('./app/assets/fonts/Raleway/static/Raleway-Medium.ttf'),
  });

  if (!loaded) {
    return null;
  }

  return ( <AppNavigation/> );
}