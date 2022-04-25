import React, {components, useState} from 'react'
import { 
    Text,
    View,
    TouchableOpacity,
    StatusBar,
    Image
} from 'react-native'

import { loginStyles } from '../styles/styles'
import MyTextInput from '../components/MyTextInput'
import colors from '../styles/colors'

export default function LoginScreen(){

        const [hidePassword, setHidePassword] = useState(false)
        
        return(
            <View style={[loginStyles.container]}>
                <StatusBar backgroundColor={colors.BLUE} translucent={true}/>
                <View style = {loginStyles.logo}>
                    <Image source={require('../assets/icon.png')}
                    style={{ height:250, width:250}}/>
                </View>
                <MyTextInput keyboardType='email-address' placeholder='E-mail' image='user'/>
                <MyTextInput keyboardType={null} placeholder='Contraseña' image='lock'
                bolGone={true} secureTextEntry={hidePassword}
                onPress={()=> setHidePassword(!hidePassword)} />
                <View style={loginStyles.btnMain}>
                    <TouchableOpacity>
                        <Text style={ loginStyles.btntxt}> Log In </Text>
                    </TouchableOpacity>
                </View>
                <View style={loginStyles.btnMain}>
                    <TouchableOpacity>
                        <Text style={ [loginStyles.btntxt, {colors: color.BLUE}]}> Register </Text>
                    </TouchableOpacity>
                </View>
                <View>
                    <TouchableOpacity>
                        <Text style={ [loginStyles.txtTransparent, {textDecorationLine: 'underline'}]}> Forgot my password </Text>
                    </TouchableOpacity>
                </View>
            </View>    
        )
    
}