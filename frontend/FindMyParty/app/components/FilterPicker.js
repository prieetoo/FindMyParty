import React from 'react';
import { StyleSheet } from 'react-native'
import RNPickerSelect from 'react-native-picker-select';

export const Dropdown = () => {

    const placeholder = {
        label: 'Filter by...',
        value: null,
        color: '#9EA0A4',
      };

    return (
        <RNPickerSelect
            placeholder = {placeholder}
            style = {pickerSelectStyles}
            onValueChange={(value) => console.log(value)}
            items={[
                { label: 'Distance', value: 'distance' },
                { label: 'Assistants', value: 'assistants' },
            ]}
        />
    );
};

const pickerSelectStyles = StyleSheet.create({
    inputIOS: {
      fontSize: 18,
      color: 'black',
    },
    inputAndroid: {
      fontSize: 16,
      color: 'black',
    },
  });