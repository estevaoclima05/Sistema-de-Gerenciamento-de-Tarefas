document.addEventListener('DOMContentLoaded', () => {
    const apiUrl = 'http://localhost:8080/api/tarefas';

    const createTaskForm = document.getElementById('create-task-form');
    const titleInput = document.getElementById('title');
    const descriptionInput = document.getElementById('description');
    const tasksList = document.getElementById('tasks');

    const editTaskForm = document.getElementById('edit-task-form');
    const editIdInput = document.getElementById('edit-id');
    const editTitleInput = document.getElementById('edit-title');
    const editDescriptionInput = document.getElementById('edit-description');
    const cancelEditButton = document.getElementById('cancel-edit');

    createTaskForm.addEventListener('submit', async (event) => {
        event.preventDefault();

        const title = titleInput.value;
        const description = descriptionInput.value;

        if (title && description) {
            await fetch(apiUrl, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ titulo: title, descricao: description }),
            });

            titleInput.value = '';
            descriptionInput.value = '';
            loadTasks();
        }
    });

    async function loadTasks() {
        const response = await fetch(apiUrl);
        const tasks = await response.json();

        tasksList.innerHTML = '';

        tasks.forEach(task => {
            const taskItem = document.createElement('li');
            taskItem.textContent = `${task.titulo} - ${task.descricao}`;
            if (task.concluida) {
                taskItem.classList.add('completed');
            }

            const markAsDoneButton = document.createElement('button');
            markAsDoneButton.textContent = task.concluida ? 'Desmarcar' : 'Concluir';
            markAsDoneButton.addEventListener('click', async () => {
                await fetch(`${apiUrl}/${task.id}/concluir`, {
                    method: 'PATCH',
                });
                loadTasks();
            });

            const editButton = document.createElement('button');
            editButton.textContent = 'Editar';
            editButton.addEventListener('click', () => {
                editIdInput.value = task.id;
                editTitleInput.value = task.titulo;
                editDescriptionInput.value = task.descricao;
                editTaskForm.style.display = 'block';
            });

            const deleteButton = document.createElement('button');
            deleteButton.textContent = 'Deletar';
            deleteButton.addEventListener('click', async () => {
                await fetch(`${apiUrl}/${task.id}`, {
                    method: 'DELETE',
                });
                loadTasks();
            });

            taskItem.appendChild(markAsDoneButton);
            taskItem.appendChild(editButton);
            taskItem.appendChild(deleteButton);
            tasksList.appendChild(taskItem);
        });
    }

    cancelEditButton.addEventListener('click', () => {
        editTaskForm.style.display = 'none';
    });

    const updateTaskForm = document.getElementById('update-task-form');
    updateTaskForm.addEventListener('submit', async (event) => {
        event.preventDefault();

        const id = editIdInput.value;
        const title = editTitleInput.value;
        const description = editDescriptionInput.value;

        if (title && description) {
            await fetch(`${apiUrl}/${id}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ titulo: title, descricao: description, concluida: false }),
            });

            editIdInput.value = '';
            editTitleInput.value = '';
            editDescriptionInput.value = '';
            editTaskForm.style.display = 'none';
            loadTasks();
        }
    });

    loadTasks();
});
