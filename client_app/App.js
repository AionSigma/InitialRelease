/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow
 */

import React, {Component} from 'react';
import { Navigation } from "react-native-navigation";
import {Platform, StyleSheet, Text, View} from 'react-native';

import UserProfile from './screens/UserProfile';
import LoanRequest from './screens/LoanRequest';
import GroupProfiles from './screens/GroupProfiles';
import Friends from './screens/Friends';
import DepositToACircle from './screens/DepositToACircle';
import CurrentCircle from './screens/CurrentCircle';

Navigation.registerComponent('aionSigma.UserProfile', () => UserProfile);
Navigation.registerComponent('aionSigma.LoanRequest', () => LoanRequest);
Navigation.registerComponent('aionSigma.GroupProfiles', () => GroupProfiles);
Navigation.registerComponent('aionSigma.Friends', () => Friends);
Navigation.registerComponent('aionSigma.DepositToACircle', () => DepositToACircle);
Navigation.registerComponent('aionSigma.CurrentCircle', () => CurrentCircle);

// Start a App
Navigation.startSingleScreenApp({
  screen: {
    screen: "aionSigma.UserProfile",
    title: "UserProfile"
  }
});
// const instructions = Platform.select({
//   ios: 'Press Cmd+R to reload,\n' + 'Cmd+D or shake for dev menu',
//   android:
//     'Double tap R on your keyboard to reload,\n' +
//     'Shake or press menu button for dev menu',
// });

// type Props = {};
// export default class App extends Component<Props> {
//   render() {
//     return (
//       <View style={styles.container}>
//         <Text style={styles.welcome}>Welcome to React Native!</Text>
//         <Text style={styles.instructions}>To get started, edit App.js</Text>
//         <Text style={styles.instructions}>{instructions}</Text>
//       </View>
//     );
//   }
// }

// const styles = StyleSheet.create({
//   container: {
//     flex: 1,
//     justifyContent: 'center',
//     alignItems: 'center',
//     backgroundColor: '#F5FCFF',
//   },
//   welcome: {
//     fontSize: 20,
//     textAlign: 'center',
//     margin: 10,
//   },
//   instructions: {
//     textAlign: 'center',
//     color: '#333333',
//     marginBottom: 5,
//   },
// });
