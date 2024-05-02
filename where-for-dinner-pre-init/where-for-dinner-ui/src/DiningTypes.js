function DiningTypes(props)
{
    return(
        <div>
            <label id="DiningTypesLable">Dining Types:</label>
            <ul>
                <li><input type="checkbox"/><label>Mexican</label></li>
                <li><input type="checkbox"/><label>Chinese</label></li>
                <li><input type="checkbox"/><label>American</label></li>
                <li><input type="checkbox"/><label>Seafood</label></li>
                <li><input type="checkbox"/><label>Greek</label></li>
                <li><input type="checkbox"/><label>Italian</label></li>
                <li><input type="checkbox"/><label>French</label></li>
            </ul>
        </div>
    );
}

export default DiningTypes;