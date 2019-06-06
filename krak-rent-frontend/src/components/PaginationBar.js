import React from "react";
import { Pagination } from "react-bootstrap";
import PaginationElements from "./PaginationElements";
import "../css/search-results.css";

const PaginationBar = props => {
  return (
    <Pagination>
      <PaginationElements
        totalPages={props.totalPages}
        activePage={props.activePage}
        onClick={props.onClick}
      />
    </Pagination>
  );
};

export default PaginationBar;