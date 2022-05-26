import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import LoginScreen from '../screens/LoginScreen';
import WelcomeScreen from '../screens/WelcomeScreen';
import RegisterFormScreen from '../screens/RegisterFormScreen';
import RecuperarPasswordScreen from '../screens/RecuperarPasswordScreen';
import MapListScreen from '../screens/MapListScreen';
import CreateEventScreen from '../screens/CreateEvent';
import EventInfoScreen from '../screens/EventInfoScreen';
import Settings from '../screens/Settings';
import EditEvent from '../screens/EditEvent'
import DeleteAccount from '../screens/DeleteUserScreen';
import FilterScreen from '../screens/FilterScreen';

const Stack = createNativeStackNavigator();

export default function MyStack() {
    return (
        <NavigationContainer>
            <Stack.Navigator>
                <Stack.Screen name="Welcome" component={WelcomeScreen} options = {{headerShown: false, headerLeft: ()=> null}} />
                <Stack.Screen name="Login" component={LoginScreen} options = {{headerShown: false}}/>
                <Stack.Screen name="Register" component={RegisterFormScreen} options = {{headerShown: false}}/>
                <Stack.Screen name="RecoverPass" component={RecuperarPasswordScreen} options = {{headerShown: false}}/>
                <Stack.Screen name="MapList" component={MapListScreen} options = {{headerShown: false}}/>
                <Stack.Screen name="CreateEvent" component={CreateEventScreen} options = {{headerShown: false}}/>
                <Stack.Screen name="EventInfo" component={EventInfoScreen} options = {{headerShown: false}}/>
                <Stack.Screen name="Settings" component={Settings} options = {{headerShown: true}}/>
                <Stack.Screen name="EditEvent" component={EditEvent} options = {{headerShown: true}}/>
                <Stack.Screen name="DeleteAccount" component={DeleteAccount} options = {{headerShown: false}}/>
                <Stack.Screen name="Filters" component={FilterScreen} options = {{headerShown: true}}/>
            </Stack.Navigator>
        </NavigationContainer>
    )
}
