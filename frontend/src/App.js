import React from "react";
import { BrowserRouter } from "react-router-dom";
import RouterComponent from "./Router";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <RouterComponent />
      </BrowserRouter>
    </div>
  );
}

export default App;
