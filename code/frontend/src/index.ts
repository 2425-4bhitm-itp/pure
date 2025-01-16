import { ToDo } from "./features/todo/todo"
import { loadAllToDos } from "./features/todo/todo-service"
import { store } from "./features/todo/model"

start()

// demo: a first subscription
store.subscribe(model => console.log("demo subscription... # of ToDos: ", model.todos.length))

async function start() {
    const body = document.querySelector("body")
    store.subscribe(model => render(body, model.todos)) // render on each change
    store.value.todos = await loadAllToDos()
    startDemo()
}

function render(base: HTMLElement, todos: ToDo[]) {
    console.log(`render ${todos.length} todos`)
    const div = document.createElement("div")

    function createRow(toDo: ToDo) {
        const rowDiv = document.createElement("div")
        rowDiv.innerHTML = /*html*/`<div class="todo">${toDo.title} completed: ${toDo.completed ? "true" : "false"}</div>`
        div.appendChild(rowDiv)
    }
    todos.forEach(createRow)
    base.firstChild.remove()
    base.appendChild(div) 
}

/** play around with changes */
function startDemo() {
    setInterval(() => {
        const model = store.value
        const todos = model.todos
        const index = Math.floor(todos.length * Math.random()) % 10
        if (todos.length > index) {
            const todo = todos[index]
            const changedTodo = {...todo, completed: !todo.completed}
            const changedTodos = [...todos]
            changedTodos[index] = changedTodo
            store.value.todos = changedTodos
            console.log("todo changed", changedTodo)
        } else {
            console.log("?!")
        }
    }, 1000)    
}