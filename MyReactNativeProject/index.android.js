/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View
} from 'react-native';
import ToastAndroid_M from './js_modules/android_toast'
var { DeviceEventEmitter } = require('react-native');


class MyReactNativeProject extends Component {
  render() {
    return (
      <View style={styles.container}>
        <Text style={styles.welcome}>
          Hello world aaaaaa  asdfasdfsdfdsdsafdsfas
        </Text>
        <Text style={styles.instructions}>
          To get started, edit index.androsffdsafdsfdid.js
        </Text>
        <Text style={styles.bigblue}>
          66666666dsfsadfdsffdsfds6666ddddsafds
        </Text>     
       
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#05FCF0',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
  bigblue: {
    color: 'blue',
    fontWeight: 'bold',
    fontSize: 30,
  },
  red: {
    color: 'red',
  },
});

AppRegistry.registerComponent('MyReactNativeProject', () => MyReactNativeProject);


ToastAndroid_M.showToast('hello,  this is android  toast.', ToastAndroid_M.LONG);
ToastAndroid_M.showDialog("我是title","我是message",(msg,which)=>{
  console.log(msg+"  "+which);
},(msg) => {
  console.log(msg);
}

);




