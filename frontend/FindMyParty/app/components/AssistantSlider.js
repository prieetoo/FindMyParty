import React, { Component } from 'react';
import { Text, View, StyleSheet } from 'react-native';
import { Constants } from 'expo';
import Slider from '@react-native-community/slider';
import searchData from '../data/searchFilters.json'


export default class AssistantSlider extends Component {
  
  constructor(props){
    super(props)
     this.state = { 
       assistants: searchData.assistants,
       minAssistants: 5,
       maxAssistants: 250
       }
  }
  

  render() {
    return (
      <View style={styles.container}>
      <Slider style={{ width: 300 }} step={1}
         minimumValue={this.state.minAssistants} maximumValue={this.state.maxAssistants}
         value={this.state.assistants} 
         onValueChange = {(val) => { 
           this.setState({ assistants: val }); 
          }}
          onSlidingComplete = {(val) => { 
            searchData.assistants = val;
            console.log(searchData.assistants)
           }}
        />
        <View style={styles.textCon}>
            <Text style={styles.colorGrey}>{this.state.minAssistants}</Text>
            <Text> {this.state.assistants} </Text>  
            <Text style={styles.colorGrey}>{this.state.maxAssistants}</Text>
        </View>
      </View>
    );
  }
}


const styles = StyleSheet.create({
  container: {
      alignSelf: 'center'
  },
  textCon: {
    width: 320,
    flexDirection: 'row',
    justifyContent: 'space-between'
  },
  colorGrey: {
    color: '#d3d3d3'
  },
  colorYellow: {
    color: 'rgb(252, 228, 149)'
  }
});
    