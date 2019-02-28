//
//  TableViewHelper.swift
//  DesafioTechFit
//
//  Created by Bruno Garcia on 27/02/19.
//  Copyright © 2019 Bruno Garcia. All rights reserved.
//

import Foundation
import UIKit

class TableViewHelper : UIView {
    
    class func showEmpty(message:String, in viewController:UITableViewController) {
        let rect: CGRect = CGRect(origin: CGPoint(x: 0, y: 0), size: viewController.view.bounds.size)
        let messageLabel = UILabel(frame: rect)
        messageLabel.text = message
        messageLabel.textColor = .white
        messageLabel.numberOfLines = 0;
        messageLabel.textAlignment = .center;
        messageLabel.font = UIFont(name: "TrebuchetMS", size: 15)
        messageLabel.sizeToFit()
        
        viewController.tableView.backgroundView = messageLabel;
        viewController.tableView.separatorStyle = .none;
    }
    
    class func hideEmpty(in viewController:UITableViewController) {
        
        viewController.tableView.backgroundView = nil;
        viewController.tableView.separatorStyle = .none;
    }
}
