import { BrowserRouter, Routes, Route } from "react-router-dom";
import Dashboard from "./components/Dashboard";
import LocalUserList from "./components/LocalUserList";
import UserList from "./components/UserList";
import PostList from "./components/PostList";

function App() {

return (

<BrowserRouter>

<Routes>

<Route path="/" element={<Dashboard/>}/>

<Route path="/localusers" element={<LocalUserList/>}/>

<Route path="/usersapi" element={<UserList/>}/>

<Route path="/postsapi" element={<PostList/>}/>

</Routes>

</BrowserRouter>

)

}

export default App