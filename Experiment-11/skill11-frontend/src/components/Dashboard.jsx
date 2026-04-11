import { Link } from "react-router-dom";

function Dashboard(){

return(

<div className="container">

<h2>React API Integration Dashboard</h2>

<div className="menu">

<Link to="/localusers">Local Users</Link>

<Link to="/usersapi">Users API</Link>

<Link to="/postsapi">Fake API Posts</Link>

</div>

</div>

)

}

export default Dashboard