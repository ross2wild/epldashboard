import './App.scss';
import {HashRouter as Router, Routes, Route} from 'react-router-dom'
import TeamPage from "./pages/TeamPage";
import MatchPage from "./pages/MatchPage";
import DashPage from "./pages/DashPage";
import Toolbar from "./components/Toolbar";

function App() {
  return (
    <div className="App">
        <Router>
            <Toolbar />
            <div className="page-content">
            <Routes>
                <Route path ="/" element={<DashPage/>}/>
                <Route path ="/teams/:teamName" element={<TeamPage/>}/>
                <Route path ="/teams/:teamName/matches/:season" element={<MatchPage/>}/>
            </Routes>
            </div>
        </Router>

    </div>
  );
}

export default App;
