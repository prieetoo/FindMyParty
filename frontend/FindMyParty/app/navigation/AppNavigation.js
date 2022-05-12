<<<<<<< HEAD
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
=======
import { createAppContainer } from 'react-navigation'
import { createStackNavigator } from 'react-navigation-stack'
import LoginScreen from '../screens/LoginScreen'
import WelcomeScreen from '../screens/WelcomeScreen'
import RegisterFormScreen from '../screens/RegisterFormScreen'
import RecuperarPasswordScreen from '../screens/RecuperarPasswordScreen'
import MapListScreen from '../screens/MapListScreen'
import CreateEventScreen from '../screens/CreateEvent'
import EventInfoScreen from '../screens/EventInfoScreen'
>>>>>>> fe8b85628f855c357223edf83076f0eb69179465

const Stack = createNativeStackNavigator();

<<<<<<< HEAD
export default function MyStack() {
    return (
        <NavigationContainer>
            <Stack.Navigator>
                <Stack.Screen name="Welcome" component={WelcomeScreen} options = {{headerShown: false}} />
                <Stack.Screen name="Login" component={LoginScreen} options = {{headerShown: false}}/>
                <Stack.Screen name="Register" component={RegisterFormScreen} options = {{headerShown: false}}/>
                <Stack.Screen name="RecoverPass" component={RecuperarPasswordScreen} options = {{headerShown: false}}/>
                <Stack.Screen name="MapList" component={MapListScreen} options = {{headerShown: false}}/>
                <Stack.Screen name="CreateEvent" component={CreateEventScreen} options = {{headerShown: false}}/>
                <Stack.Screen name="EventInfo" component={EventInfoScreen} options = {{headerShown: false}}/>
                <Stack.Screen name="Settings" component={Settings} options = {{headerShown: true}}/>
            </Stack.Navigator>
        </NavigationContainer>
    )
}
=======
        Login:{
            screen: LoginScreen,
            navigationOptions:{
                headerShown: false,
            }
        },
        
        RecuperarPassword:{
            screen: RecuperarPasswordScreen,
            navigationOptions:{
                headerShown: false,
            }
        },

        Registro:{
            screen: RegisterFormScreen,
            navigationOptions:{
                headerShown: false,
            }
        },

        MapList:{
            screen: MapListScreen,
            navigationOptions:{
                headerShown: false,
            }
        },

        CreateEvent:{
            screen: CreateEventScreen,
            navigationOptions:{
                headerShown: false,
            }
        },

        EventInfo:{
            screen: EventInfoScreen,
            navigationOptions:{
                headerShown: false,
            }
        },
    }
)

export default createAppContainer(AppNavigation)
>>>>>>> fe8b85628f855c357223edf83076f0eb69179465
