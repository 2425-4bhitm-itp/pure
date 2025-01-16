import { ToDo } from "./features/todo/todo"
import { loadAllToDos } from "./features/todo/todo-service"
import { store } from "./features/todo/model"

start()

store.subscribe(model => {
    //console.log("Anzahl Todos: ", model.todos.length)
})

async function start() {
    const body = document.querySelector("body")
    store.subscribe(model => {
        console.log("model changed", model)
        render(body, model.todos)
    })
    store.value.todos = await loadAllToDos()
    demo()
}

function render(base: HTMLElement, todos: ToDo[]) {
    const div = document.createElement("div")

    function createRow(toDo: ToDo) {
        const rowDivHtml = /*html*/`
            <div class="todo">${toDo.title} completed: ${toDo.completed ? "true" : "false"}</div>
        `
        const rowDiv = document.createElement("div")
        rowDiv.innerHTML = rowDivHtml
        div.appendChild(rowDiv)
    }
    todos.forEach(createRow)
    base.innerHTML = ""
    base.appendChild(div) 
}

/** play around with changes */
function demo() {
    setInterval(() => {
        const model = store.value
        const todos = model.todos
        const index = Math.floor(todos.length * Math.random()) % 10
        if (todos.length > index) {
            console.log("index", index)
            const todo = todos[index]
            const changedTodo = {...todo, completed: !todo.completed}
            const changedTodos = [...todos]
            changedTodos[index] = changedTodo
            store.value.todos = changedTodos
            console.log("changed", index, changedTodo)
        } else {
            console.log("?!")
        }
    }, 500)    

}