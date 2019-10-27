const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');

//In React, uppercase is the convention for naming components.
//This component serves as the top level container for all components
class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {employees: []};
    }

    //componentDidMount is the API invoked after React renders a component in the DOM.
    componentDidMount() {
        client({method: 'GET', path: '/api/employees'}).done(response => {
                this.setState({employees: response.entity._embedded.employees})
        });
    }

    //render is the API to "draw" the component on the screen.
    render() {
        return (
            <EmployeeList employees={this.state.employees}/>
        )
    }
}

class EmployeeList extends React.Component {
    render() {
        const employees = this.props.employees.map(employee =>
            <Employee key={employee._links.self.href} employee={employee}/> //this is creating a new component called 'Employee'
        );
        return (
            <table>
                <tbody>
                    <tr>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Description</th>
                    </tr>
                    {employees}
                </tbody>
            </table>
        )
    }
}

class Employee extends React.Component {
    render() {
        return (
            <tr>
                <td>{this.props.employee.firstName}</td>
                <td>{this.props.employee.lastName}</td>
                <td>{this.props.employee.description}</td>
            </tr>
        )
    }
}

ReactDOM.render(
    <App />,
    document.getElementById('react') //refers to the div id in /resources/templates/index.html
)