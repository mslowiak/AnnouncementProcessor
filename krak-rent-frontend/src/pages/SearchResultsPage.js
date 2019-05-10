import React, { Component } from 'react';

class SearchResultsPage extends Component {
  render() {
    let searchJsonBody = this.props.location.state.searchJsonBody
    return (
      <div>SearchResultsPage page</div>
    );
  }
}

export default SearchResultsPage;