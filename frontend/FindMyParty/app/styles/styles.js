import { StyleSheet } from 'react-native'
import { getStatusBarHeight } from 'react-native-status-bar-height'
import color from '../styles/colors'

const statusBarHeight = getStatusBarHeight();

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
        justifyContent: 'center',

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

// Styles for WelcomeScreen, LoginScreen, RegisterScreen
const logStyles = StyleSheet.create({

    logoBox: {
        alignItems: 'center',
    },

    button: {
        backgroundColor: 'rgb(63, 152, 246)',
        width: 300,
        height: 50,
        alignItems: 'center',
        justifyContent: 'center',
        borderRadius: 8,
    },

    container: {
        flex: 3,
        flexDirection: 'column',
        backgroundColor: "white",
        alignItems: 'center',
        justifyContent: 'space-evenly',
        
    },

    statusbar: {
        backgroundColor: "#020",
    },

    titleBox: {
        marginTop: 20,
        marginBottom: 20,
        flexDirection: 'row',
        alignItems: 'center',
    },

    titleBoxRegister: {
        marginTop: 20,
        flexDirection: 'row',
        alignItems: 'center',
    },

    formFieldsBoxLogin: {
        flex: 1,
        justifyContent: 'space-around',
        paddingBottom: 5,
    },

    formFieldsBoxRegister: {
        flex: 1,
        justifyContent: 'space-between',
        paddingVertical: 20,
    },

    title: {
        flex: 1,
        flexDirection: 'row',
        marginHorizontal: 30,
        fontSize: 30,
        fontFamily: 'RalewayTitle',
        textAlign: 'center',
    },

    formFields: {
        backgroundColor: 'rgb(248, 248, 248)',
        width: 350,
        height: 50,
        borderRadius: 8,
        paddingHorizontal: 15,
        fontFamily: 'RalewayUI',
    },

    mainButton: {
        width: 300,
        height: 50,
        alignItems: 'center',
        justifyContent: 'center',
        borderRadius: 8,
        marginBottom: 10,
    },

    secondaryButton: {
        backgroundColor: 'rgb(255, 255, 255)',
        width: 300,
        height: 50,
        alignItems: 'center',
        justifyContent: 'center',
        borderRadius: 8,
    },

    loginBox: {
        flex: 3,
        alignItems: 'center',
        paddingTop: 20,
    },

    registerSecondary: {
        fontFamily: 'RalewayUI',
        fontSize: 20
    },

    proceedBox: {
        flex: 5,
        alignItems: 'center',
        justifyContent: 'flex-start',
    },

    registerBox: {
        flex: 1,
        alignItems: 'center',
    },

    loginSpace: {
        flex: 1,
        flexDirection: 'row',
        alignItems: 'flex-end',
    },

    loginText: {
        color: 'rgb(63, 152, 246)',
    },

    checkBox: {
        marginLeft: 0,
        marginRight: 0,
        borderWidth: 0,
        backgroundColor: color.WHITE,
    },

    containerSocial: {
        paddingTop: 5,
        alignItems: 'center',
        marginBottom: 10
    },

    buttonSocialIcon: {
        width: 300,
        height: 50,
        alignItems: 'center',
        borderRadius: 8,
        fontFamily: 'RalewayUI',
        fontSize: 50,
    },
})

const listEvents = StyleSheet.create({

    statusBarBlur: {
        position: 'absolute',
        top: 0,
        left: 0,
        right: 0,
        height: statusBarHeight,
      },

    titleBox: {
        flexDirection: 'row',
        paddingTop: 5,
        paddingRight: 20,
        justifyContent: "space-between",
        textAlignVertical: "center",
    },

    actionsBox: {
        flexDirection: 'row',
        justifyContent: 'space-between',
        alignItems: 'center',
        borderTopStartRadius: 20,
        borderTopEndRadius: 20,
        borderTopColor: 'rgb(220, 220, 220)',
        paddingTop: 10,
        paddingBottom: 15,
        borderBottomWidth: 1,
        borderBottomColor: 'rgb(230, 230, 230)',

    },

    title: {
        flexDirection: 'row',
        fontSize: 27,
        fontFamily: 'RalewayTitleBold',
        paddingLeft: 10,
    },

    firstEventElement: {
        flexDirection: 'row',
        height: 80,
        alignItems: 'center',
    },

    eventElement: {
        flexDirection: 'row',
        borderTopWidth: 1,
        borderTopStartRadius: 40,
        borderTopEndRadius: 40,
        borderTopColor: 'rgb(230, 230, 230)',
        height: 80,
        alignItems: 'center',
    },

    eventPic:  {
        width: 50,
        height: 50,
        borderRadius: 50,
        marginLeft: 18,
    },

    eventDetails: {
        flexDirection: 'column',
        marginLeft: 7,
    },

    eventTitle: {
        fontWeight: 'bold',
        fontSize: 16.5,
        paddingLeft: 10,
    },

    eventDistance: {
        color: 'rgb(100, 100, 100)',
        paddingLeft: 10,
    },

    eventNavigate: {
        width: 30,
        height: 30,
        transform:[{ rotateY: '180deg' }],
        position: 'absolute',
        right: 15,
    },

    eventScroll: {
        height: 670,
    },

    listCreateEvent: {
        fontSize: 17,
        color: 'rgb(62, 167, 253)',
        paddingTop: 8,
        marginLeft: 20,
    },

    listFilters: {
        fontSize: 17,
        color: 'rgb(62, 167, 253)',
        paddingTop: 8,
        marginRight: 20,
    },

    fabutton: {
        bottom: 220,
        right: 40
    },

    fabuttonView: {
        flex: 1,
        backgroundColor: '#fff',
    },

    filterDropdownView: {
        paddingTop: 20,
        paddingLeft: 20,
    },

    settingsOptions: {
        flexDirection: 'row',
        borderBottomWidth: 1,
        borderBottomColor: 'rgb(230, 230, 230)',
        height: 80,
        alignItems: 'center',
    },

    containerFilters: {
        flex: 3,
        flexDirection: 'column',
        backgroundColor: "white",
    },

})

export { mainStyles, logStyles, listEvents }