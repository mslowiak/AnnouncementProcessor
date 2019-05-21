import React, { Component } from 'react';
import { Alert } from 'react-bootstrap';

class ErrorLabel extends Component {

  constructor(props) {
    super(props);

    this.state = { show: true };
  }

  render() {
    const handleHide = () => this.setState({ show: false });
    let component = null;
    if(this.props.isVisible){
        component = <Alert 
                        dismissible 
                        show={this.state.show} 
                        variant={'danger'}
                        onClose={handleHide}
                    > 
                      {this.props.errorMessage} 
                    </Alert>;
    }
    console.log(this.props.isError)
    return (
        <div>
            {component}
        </div>
    );
  }
}

export default ErrorLabel;