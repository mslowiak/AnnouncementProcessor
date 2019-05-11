import React, { Component } from 'react';
import Axios from 'axios'

class SearchResultsPage extends Component {
    componentDidMount() {
        console.log(this.props.location.state.searchJsonBody)
        const config = {headers: {'Content-Type': 'application/json'}};
        Axios.post('announcements/get/search-results', this.props.location.state.searchJsonBody, config)
            .then(
                results => console.log(results.data.content)
            )
    }

  render() {
    return (
      <div>SearchResultsPage page</div>
    );
  }
}

export default SearchResultsPage;