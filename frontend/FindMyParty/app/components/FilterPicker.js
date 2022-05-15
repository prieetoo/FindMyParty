import React from 'react';
import { StyleSheet } from 'react-native'
import RNPickerSelect from 'react-native-picker-select';

export const Dropdown = () => {

    const placeholder = {
        label: 'Filter by...',
        value: null,
        color: 'black',
      };

    return (
        <RNPickerSelect
            placeholder = {placeholder}
            style = {pickerSelectStyles}
            onValueChange={(value) => console.log(value)}
            items={[
                { label: 'Up next', value: 'upNext'},
                { label: 'Distance', value: 'distance' },
                { label: 'Assistants', value: 'assistants' },
                { label: 'Host rating', value: 'hostRating'},
                { label: 'Paid', value: 'isPaid'},
            ]}
        />
    );
};

const pickerSelectStyles = StyleSheet.create({
    inputIOS: {
      fontSize: 17,
      color: 'rgb(62, 167, 253)',
      marginRight: 25,
    },
    inputAndroid: {
      fontSize: 17,
      color: 'rgb(62, 167, 253)',
    },
  });