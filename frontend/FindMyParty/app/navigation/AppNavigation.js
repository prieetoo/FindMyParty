import { createAppContainer } from 'react-navigation'
import { createStackNavigator } from 'react-navigation-stack'
import LoginScreen from '../screens/LoginScreen'
import WelcomeScreen from '../screens/WelcomeScreen'
import RegisterFormScreen from '../screens/RegisterFormScreen'
import RecuperarPasswordScreen from '../screens/RecuperarPasswordScreen'
import MapListScreen from '../screens/MapListScreen'

const AppNavigation = createStackNavigator({
    
        Welcome:{
            screen: WelcomeScreen,
            navigationOptions:{
                headerShown: false,
            }
        },

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
    }
)

export default createAppContainer(AppNavigation)
