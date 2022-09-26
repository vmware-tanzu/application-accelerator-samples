import TimeWindow from "./TimeWindow";

function Availability(props)
{
    const timeWindows = props.avail.availabilityWindows.map((win, index)=>{
        return <TimeWindow id={index} key={index} timeWin={win}/>
    });

    return(
        <div className="availability">
            <label className="diningLabel">{props.avail.diningName}</label><br/>
            <label className="generalLabel">Address: </label>
            <label>{props.avail.address + '  ' + props.avail.locality + ',' + props.avail.region + ' ' + props.avail.postalCode}</label>
            <br/>
            <label className="generalLabel"> Phone Number:</label>
            <label>{props.avail.phoneNumber}</label><br/>
            <a className="generalLabel" href={props.avail.reservationURL} target="_blank" rel="noopener noreferrer">Reservations</a>
            {timeWindows.length ? <div><label className="timeWindowHeader">Availability</label>{timeWindows}</div> : <div><label className="noTimeWindows">NO AVAILABILITY</label></div>}
            <p/>
        </div>
    )
}

export default Availability;