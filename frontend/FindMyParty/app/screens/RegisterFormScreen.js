import React, { useState } from 'react'
import {
    Text,
    View,
    TouchableOpacity,
    StatusBar,
    ScrollView
} from 'react-native'
import { mainStyles, registroStyles } from '../styles/styles'
import MyTextInput from '../components/MyTextInput'
import ToolBar from '../components/ToolBar'
import color from '../styles/colors'
import { CheckBox, SocialIcon, Button } from 'react-native-elements'

function goToScreen(props, routeName) {
    props.navigation.navigate(routeName)
}

export default function RegistroScreen(props) {

    const [hidePassword, setHidePassword] = useState(false)

    return (
        <ScrollView
            keyboardDismissMode='on-drag'
            keyboardShouldPersistTaps='always'
            style={{ backgroundColor: color.WHITE }}>
            <StatusBar backgroundColor={color.BLUE} translucent={true} />
            <ToolBar titulo='Registrarse'
                onPressLeft={() => goToScreen(props, 'Login')}
                iconLeft={require('../assets/back.png')} />
            <View style={[mainStyles.container, { padding: 50 }]}>
                <Text style={mainStyles.titleText}> Crea tu Cuenta</Text>
                <MyTextInput placeholder='Nombres' image='user' />
                <MyTextInput placeholder='Apellidos' image='user' />
                <MyTextInput keyboardType='email-address' placeholder='E-mail'
                    image='envelope' />
                <MyTextInput keyboardType={null} placeholder='Contraseña'
                    onPress={() => setHidePassword(!hidePassword)}
                    image='lock' bolGone={true}
                    secureTextEntry={hidePassword}
                />
                <CheckBox
                    containerStyle={registroStyles.checkBox}
                    textStyle={{ color: color.BLUE }}
                    title='He leído y acepto los términos y condiciones'
                    checked={false}
                    checkedColor={color.BLUE}
                />
                <View style={mainStyles.btnMain}>
                    <TouchableOpacity onPress={() =>
                        goToScreen(props, 'Login')}>
                        <Text style={mainStyles.btntxt}>Registrarse</Text>
                    </TouchableOpacity>
                </View>
                <View style={{ flex: 1, flexDirection: 'row', alignItems: 'center' }}>
                    <Text style={{ color: color.BLUE }}>¿Ya tienes una cuenta? </Text>
                    <Button title="Inicia Sesión" onPress={() => goToScreen(props, 'Login')} type="clear" />
                </View>
                <View style={registroStyles.containerSocial}>
                    <SocialIcon
                        style={registroStyles.buttonSocialIcon}
                        title='Iniciar con Facebook'
                        button
                        type='facebook'
                    />
                    <SocialIcon
                        style={registroStyles.buttonSocialIcon}
                        title='Iniciar con Google'
                        button
                        type='google-plus-official'
                    />
                </View>
            </View>
        </ScrollView>
    )
}