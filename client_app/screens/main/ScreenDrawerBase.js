import React, { Component } from 'react';
import PropTypes from 'prop-types';

class ScreenDrawerBase extends Component {
    constructor(props) {
        super(props);
        this.props.navigator.setOnNavigatorEvent(this.onNavigatorEvent);
    }

    onNavigatorEvent = event => {
        if (event.type === "NavBarButtonPress") {
            if (event.id === "sideDrawerToggle") {
                this.props.navigator.toggleDrawer({
                    side: "left"
                });
            }
        }
    }
}
ScreenDrawerBase.propTypes = {
    navigator: PropTypes.shape({
        setOnNavigatorEvent: PropTypes.func.isRequired,
        toggleDrawer: PropTypes.func.isRequired,
    }).isRequired,
};
export default ScreenDrawerBase;