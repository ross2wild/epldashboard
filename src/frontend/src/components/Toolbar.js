import {React} from 'react';
import {Link} from 'react-router-dom';
import "./Toolbar.scss";

export const Toolbar = () => {
    return (
        <div className="Toolbar">
            <div className="logo"><Link to="/">EPL Dashboard</Link></div>
            <nav className="nav-links">
                <Link to="/">Home</Link>
            </nav>
        </div>
    );
};

export default Toolbar;