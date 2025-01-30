import './App.scss';
import {BrowserRouter as Router, Routes, Route} from 'react-router-dom'
import TeamPage from "./pages/TeamPage";
import MatchPage from "./pages/MatchPage";
import {DashPage} from "./pages/DashPage";

function App() {
  return (
    <div className="App">
        <Router>
            <Routes>
                <Route path ="/" element={<DashPage/>}/>
                <Route path ="/teams/:teamName" element={<TeamPage/>}/>
                <Route path ="/teams/:teamName/matches/:season" element={<MatchPage/>}/>
            </Routes>
        </Router>
    </div>
  );
}

export default App;
