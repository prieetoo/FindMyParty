import React, { useState, useContext} from 'react'
import { Text, View, SafeAreaView, TouchableOpacity, TouchableWithoutFeedback, Keyboard, Image, TextInput } from 'react-native'
import { mainStyles, loginStyles } from '../styles/styles'
import MyTextInput from '../components/MyTextInput'
import MyButton from '../components/MyButton'
import color from '../styles/colors'

export default function LoginScreen(props){

  
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const [hidePassword, setHidePassword] = useState(false)

    return(
        <DismissKeyboard>
            <SafeAreaView style={[mainStyles.container, {padding: 50}]}>
                
                <View style={loginStyles.logo}>
                    <Image source={require('../assets/icon.png')}
                    style={{ height:250, width:250}}/>    
                </View>

                <View style = {mainStyles.formFieldsBox}>
                    <TextInput style = {mainStyles.formFields} keyboardType='email-address' placeholder='E-mail' image='user'
                    value={email} onChangeText={(email)=> setEmail(email)}/>
                    <TextInput style = {mainStyles.formFields} keyboardType={null} placeholder='Contraseña' image='lock' bolGone={true}
                    secureTextEntry={hidePassword}
                    onPress={() => setHidePassword(!hidePassword)}
                    value={password} onChangeText={(password)=> setPassword(password)}/>
                </View>

                <MyButton
                    titulo='Iniciar Sesión'
                    onPress={()=> iniciarSesion()}
                />
                <MyButton
                    transparent={true}
                    titulo='Registrarse'
                    onPress={()=> goToScreen('Registro')}
                />
                <View>
                    <TouchableOpacity onPress={() => goToScreen('RecuperarPassword')}>
                        <Text style={ [mainStyles.txtTransparent, { textDecorationLine: 'underline'}]}>Olvide mi Contraseña</Text>
                    </TouchableOpacity>
                </View>
            </SafeAreaView>
        </DismissKeyboard>

    )

    function iniciarSesion(){
        loginAction({
            type:'sign', data:{
                email, password
            }
        })
        goToScreen('Main')
    }

    function goToScreen(routeName){
        props.navigation.navigate(routeName)
    }
    
}

const DismissKeyboard = ({ children }) => (
    <TouchableWithoutFeedback onPress = {() => Keyboard.dismiss()}> 
    {children}
    </TouchableWithoutFeedback>
    );