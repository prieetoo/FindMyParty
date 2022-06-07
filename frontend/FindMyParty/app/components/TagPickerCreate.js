import React from 'react';
import { StyleSheet } from 'react-native'
import RNPickerSelect from 'react-native-picker-select';
import eventCreation from '../data/eventCreation.json'

export const TagDropdownC = () => {

    const placeholder = {
        label: 'Select a tag...',
        value: null,
        color: 'black',
      };

    return (
        <RNPickerSelect
            placeholder = {placeholder}
            style = {pickerSelectStyles}
            onValueChange={(value) => {
              eventCreation.tags = value;
              console.log(eventCreation.tags)
            }}
            items={[
                { label: 'Rave', value: 'rave'},
                { label: 'Reggaeton', value: 'reggaeton' },
                { label: 'Pool', value: 'pool' },
            ]}
        />
    );
};

const pickerSelectStyles = StyleSheet.create({
    inputIOS: {
      fontSize: 17,
      color: 'rgb(0, 0, 0)',
      marginLeft: 4,
    },
    inputAndroid: {
      fontSize: 17,
      color: 'rgb(62, 167, 253)',
    },
  });