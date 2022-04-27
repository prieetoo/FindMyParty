import { createAppContainer } from 'react-navigation'
import { createStackNavigator } from 'react-navigation-stack'
import LoginScreen from '../screens/LoginScreen'
import MainScreen from '../screens/MainScreen'
import RegisterFormScreen from '../screens/RegisterFormScreen'
import RecuperarPasswordScreen from '../screens/RecuperarPasswordScreen'

const AppNavigation = createStackNavigator({
    
    Login:{
        screen: LoginScreen,
        navigationOptions:{
            headerShown: false,
        }
    },
    Main:{
        screen: MainScreen,
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
})

export default createAppContainer(AppNavigation)
