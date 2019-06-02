import React, { Component } from "react";
import Axios from "axios";
import GeneralAnnouncementContainer from "../components/GeneralAnnouncementContainer";
import PaginationBar from "../components/PaginationBar";
import "../css/search-results.css";

class SearchResultsPage extends Component {
  constructor(props) {
    super(props);
    this.state = {
      announcements: [],
      pageSize: 10,
      totalPages: 0,
      activePage: 0
    };
  }

  requestAnnouncementsData(page, size, body, config) {
    Axios.post(
      "announcements/get/search-results?page=" + page + "&size=" + size,
      body,
      config
    ).then(results => {
      this.setState({
        announcements: results.data.content,
        pageSize: size,
        totalPages: results.data.totalPages,
        activePage: results.data.pageable.pageNumber
      });
    });
  }

  componentDidMount() {
    const config = { headers: { "Content-Type": "application/json" } };
    this.requestAnnouncementsData(
      0,
      this.state.pageSize,
      this.props.location.state.searchJsonBody,
      config
    );
  }

  handlePaginationClick(pageNum) {
    const config = { headers: { "Content-Type": "application/json" } };
    this.requestAnnouncementsData(
      pageNum,
      this.state.pageSize,
      this.props.location.state.searchJsonBody,
      config
    );
  }

  render() {
    const dataComponent =
      this.state.announcements && this.state.announcements.length ? (
        <GeneralAnnouncementContainer data={this.state.announcements} />
      ) : (
        <div>Loading...</div>
      );

    const paginationComponent =
      this.state.announcements && this.state.announcements.length ? (
        <PaginationBar
          totalPages={this.state.totalPages}
          activePage={this.state.activePage}
          onClick={i => this.handlePaginationClick(i)}
        />
      ) : (
        <div>Loading...</div>
      );

    return (
      <div className="search-result-main">
        <div className="results-info">
          <div>Wyniki wyszukiwania</div>
        </div>
        {dataComponent}
        {paginationComponent}
      </div>
    );
  }
}

export default SearchResultsPage;
