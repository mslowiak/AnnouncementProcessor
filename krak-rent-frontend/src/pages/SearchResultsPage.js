import React, { Component } from 'react';

class SearchResultsPage extends Component {
  render() {
    console.log(this.props.location.state.search)
    return (
      <div>SearchResultsPage page</div>
    );
  }
}

export default SearchResultsPage;