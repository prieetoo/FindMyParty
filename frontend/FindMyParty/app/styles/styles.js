import { StyleSheet, Keyboard } from 'react-native'
import color from '../styles/colors'

//Styles for MainScreen
const mainStyles = StyleSheet.create({

    container: {
        flex: 1,
        alignItems: 'center',
        backgroundColor: color.WHITE
    },

    containerCenter: {
        paddingTop: 10,
        alignItems: 'center',
        marginBottom: 25,
    },

    titleText: {
        fontSize: 28,
        marginTop: 20,
        color: color.BLUE,
    },
    
    formFields: {
        backgroundColor: 'rgb(248, 248, 248)',
        width: 350,
        height: 50,
        borderRadius: 8,
        paddingHorizontal: 15,
    },

    formFieldsBox: {
        flex: 1,
        justifyContent: 'space-evenly',

    },

    btnMain: {
        width: 280,
        marginTop: 40,
        marginBottom: 20,
        alignItems: 'center',
        backgroundColor: color.BLUE,
        borderRadius: 60
    },

    btnTransparent: {
        backgroundColor: 'rgba(52, 52, 52, 0)',
        borderColor: color.BLUE,
        width: 280,
        borderWidth: 2,
        marginBottom: 20,
        borderRadius: 60
    },

    btntxt: {
        textAlign: 'center',
        fontSize: 17,
        color: color.WHITE,
        paddingVertical: 15,
    },

    txtTransparent: {
        color: color.LIGHTPRIMARYCOLOR,
        fontSize: 14,
    }
    
})

//Styles for LoginScreen
const loginStyles = StyleSheet.create({

    logo: {
        paddingTop: 50,
        alignItems: 'center',
    },
})

//Styles for RegisterScreen
const registerStyles = StyleSheet.create({

    checkBox: {
        marginLeft: 0,
        marginRight: 0,
        borderWidth: 0,
        backgroundColor: color.WHITE,
    },

    containerSocial: {
        paddingTop: 30,
        alignItems: 'center',
        marginBottom: 10,
    },

    buttonSocialIcon: {
        marginBottom: 10,
        width: 300,
        height: 50,
        alignItems: 'center',
        borderRadius: 8,
        fontFamily: 'RalewayUI',
        fontSize: 50,
    },
})

export { loginStyles, mainStyles, registerStyles }