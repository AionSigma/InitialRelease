import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { StyleSheet, Image  } from 'react-native';
import images from './../assets';

const styles = StyleSheet.create({
  // container: {
  //   flex: 1,
  //   alignItems: 'center',
  //   justifyContent: 'center',
  //   paddingTop: 30,
  //   backgroundColor: '#ecf0f1',
  // },
  // image: {
  //   height: 80,
  //   width: 80,
  //   borderRadius: 40,
  // }
});

class ImageCircle extends Component {
    render() {
        let {image,size} = this.props;
        image = image || images.avatar_playholder;
        size = size || 40;
        const borderRadius = size/2;
        return (

        <Image 
          //style={styles.image}
          style={{width:size, height:size, borderRadius}}
          resizeMode={"cover"}
          source={image}
        />

        );
    }
}

ImageCircle.propTypes = {
  image: PropTypes.any,
  size: PropTypes.number,
};




export default ImageCircle;

