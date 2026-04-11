import { useState,useEffect } from "react"
import axios from "axios"

function PostList(){

const [posts,setPosts] = useState([])
const [filter,setFilter] = useState("")

const loadPosts = ()=>{

axios.get("http://localhost:8080/api/posts")

.then(res=>{
setPosts(res.data)
})

}

useEffect(()=>{
loadPosts()
},[])

const filteredPosts = filter
? posts.filter(p=>p.userId==filter)
: posts

return(

<div className="container">

<h2>Posts From Backend</h2>

<button onClick={loadPosts}>Refresh</button>

<br/><br/>

<select onChange={(e)=>setFilter(e.target.value)}>

<option value="">All Users</option>
<option value="1">User 1</option>
<option value="2">User 2</option>
<option value="3">User 3</option>

</select>

<div className="posts">

{filteredPosts.map(post=>(

<div key={post.id} className="post">

<h4>{post.title}</h4>
<p>{post.body}</p>

</div>

))}

</div>

</div>

)

}

export default PostList