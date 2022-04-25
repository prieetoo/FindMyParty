import React from "react";
import RegisterFormScreen from './app/screens/RegisterFormScreen';
import { useFonts } from 'expo-font';
import MainScreen from "./app/screens/MainScreen";

export default function App() {
  
  const [loaded] = useFonts({
    Karantina: require('./app/assets/fonts/Karantina/Karantina-Bold.ttf'),
    RalewayLight: require('./app/assets/fonts/Raleway/static/Raleway-Light.ttf'),
  });

  if (!loaded) {
    return null;
  }

  return <MainScreen />;
}