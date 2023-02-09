import React from "react";
import UserRegister from './components/login/UserRegister';
import { BrowserRouter as  Router, Routes, Route, Link} from "react-router-dom";
import Login from "./components/login/login";
import Home from "./components/Home";


function App() {
  return (
    <div className="App">
      
        <Router>
          <Routes>
           
              <Route exact path="/" element={<Home/>}/>
              <Route path="/login/Userregister" element={<UserRegister/>} />
              <Route path="/login/" element={<Login/>} />
            
          </Routes>
        </Router>
      
    </div>
  );
}

export default App;
