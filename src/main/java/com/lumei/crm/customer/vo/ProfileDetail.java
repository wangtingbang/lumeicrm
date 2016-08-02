package com.lumei.crm.customer.vo;

import java.util.List;

import com.lumei.crm.customer.dto.Notes;
import com.lumei.crm.customer.dto.Profile;

public class ProfileDetail extends Profile{
  private List<Notes> notes;

  public List<Notes> getNotes() {
    return notes;
  }

  public void setNotes(List<Notes> notes) {
    this.notes = notes;
  }
}
