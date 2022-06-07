import React from 'react'
import {
    Text,
    TouchableOpacity,
} from 'react-native'
import { mainStyles } from '../styles/styles'
import color from '../styles/colors'

function MyButton(props) {

    const sButton = props.transparent?mainStyles.btnTransparent:mainStyles.btnMain
    const sText = props.transparent?{color: color.BLUE}: null
    
    return (
        <TouchableOpacity style={[sButton, props.style]}
            onPress={ props.onPress}>
            <Text style={[mainStyles.btntxt, sText]}>{props.titulo}</Text>
        </TouchableOpacity>
    )
}

export default MyButton